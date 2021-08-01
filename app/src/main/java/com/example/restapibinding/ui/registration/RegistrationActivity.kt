package com.example.restapibinding.ui.registration

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.restapibinding.R
import com.example.restapibinding.data.db.entities.User
import com.example.restapibinding.data.network.Resource
import com.example.restapibinding.data.network.responses.pincode.PincodeDetails
import com.example.restapibinding.databinding.ActivityRegistrationBinding
import com.example.restapibinding.security.mobileNumberValidation
import com.example.restapibinding.security.validateET
import com.example.restapibinding.ui.weather.WeatherActivity
import com.example.restapibinding.util.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RegistrationActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory : RegistrationVMF by instance()

    private lateinit var binding : ActivityRegistrationBinding
    private lateinit var viewModel: RegistrationVM

    private lateinit var view: View

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        view = binding.root
        viewModel = ViewModelProvider(this, factory).get(RegistrationVM::class.java)
        setContentView(view)

        val items = listOf("Male", "Female")
        val adapter = ArrayAdapter(this, R.layout.item_gender, items)
        binding.etGender.setAdapter(adapter)

        binding.etDOB.setOnClickListener {
            handleDateButton(this, etDOB)
        }

        binding.checkPincode.setOnClickListener {
            checkPincodeDetails()
        }

        binding.btnRegister.setOnClickListener {
            registration()
        }

        binding.etPincode.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?){
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length == 6)
                    binding.checkPincode.show()
                else {
                    binding.checkPincode.gone()
                    binding.tvDistrict.text = ""
                    binding.tvState.text = ""
                }
            }
        })
    }

    private fun checkPincodeDetails(){
        val pincode = binding.etPincode.editText?.text.toString()

        viewModel.getPinCodeDetails(pincode)

        viewModel.pincodeDetails.observe(this, {
            when (it) {
                is Resource.Success -> {
                    onSuccess(it.value)
                }
                is Resource.Loading -> {
                }
                is Resource.Failure -> {
                    handleApiError(it, this, view)
                    Log.d("ONFail", "OnFailure")
                }
            }
        })
    }

    private fun onSuccess(response: PincodeDetails){
        log("${response[0].PostOffice}")
        if(response[0].Status == "Success"){
            binding.tvDistrict.text = response[0].PostOffice[0].District
            binding.tvState.text = response[0].PostOffice[0].State
        }
        else{
            toast("No data found.")
            log("No data found.")
        }

    }

    private fun registration(){

        if (binding.etDOB.text.isEmpty()) {
            binding.etDOB.error = "Date is not choose"
            log("Date is not choose")
            return
        }

        if(!mobileNumberValidation(binding.etMobileNumber.editText?.text.toString())){
            binding.etMobileNumber.error = "Mobile number required."
            return
        }

        if(!validateET(binding.etFullName.editText!!) || !validateET(binding.etDOB) ||
            !validateET(binding.etAddressLine1.editText!!) || !validateET(binding.etPincode.editText!!) ||
            binding.etGender.text == null
        ){
            return
        }
        val addressLine1 = binding.etAddressLine1.editText?.text.toString()
        val addressLine2 = binding.etAddressLine2.editText?.text.toString()
        val pincode = binding.etPincode.editText?.text.toString()
        val district = binding.tvDistrict.text.toString()
        val state = binding.tvState.text.toString()
        val address = "$addressLine1 $addressLine2, Dis- $district, State- $state"

        lifecycleScope.launch{
            val user = User(1,binding.etMobileNumber.editText?.text.toString().toLong(),
            binding.etFullName.editText?.text.toString(), binding.etGender.text.toString(),
            binding.etDOB.text.toString(), address, pincode.toInt())

            viewModel.saveUser(user)
        }

        toast("Saved to room data.")
        startActivity(Intent(this, WeatherActivity::class.java))
        finish()
    }
}