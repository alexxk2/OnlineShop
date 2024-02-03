package com.example.onlineshop.presentation.registration.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentRegistrationBinding
import com.example.onlineshop.presentation.registration.view_model.RegistrationViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegistrationViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater,container,false)
        configureStatusBar()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPhoneMaskOnEditText()

        binding.etFirstName.addTextChangedListener {
            validateNames(it,binding.tiFirstName,binding.etFirstName)
        }

        binding.etLastName.addTextChangedListener {
            validateNames(it,binding.tiLastName,binding.etLastName)
        }

        binding.etPhoneNumber.addTextChangedListener {
            validatePhone(it)
        }

        viewModel.isAllFieldsValid.observe(viewLifecycleOwner){
            binding.btnLogin.isEnabled = it
        }

        binding.btnLogin.setOnClickListener {
            viewModel.saveUserCredentials(
                firstName = binding.etFirstName.text,
                lastName = binding.etLastName.text,
                phoneNumber = binding.etPhoneNumber.text
            )
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

    private fun validateNames(
        text: Editable?,
        textInput: TextInputLayout,
        textEdit: TextInputEditText
    ) {
        viewModel.isAllInputsValid(
            binding.etFirstName.text,
            binding.etLastName.text,
            binding.etPhoneNumber.text
        )

        if (viewModel.isTextInputValid(text)) {
            hideError(textInput, textEdit)

        } else {
            showError(textInput,textEdit,getString(R.string.error_names))
        }
    }

    private fun validatePhone(text: Editable?) {

        viewModel.isAllInputsValid(
            binding.etFirstName.text,
            binding.etLastName.text,
            binding.etPhoneNumber.text
        )

        if (viewModel.isPhoneInputValid(text)) {
            hideError(binding.tiPhoneNumber, binding.etPhoneNumber)
        } else {
            showError(
                binding.tiPhoneNumber,
                binding.etPhoneNumber,
                getString(R.string.error_phone)
            )
        }
    }


    private fun showError(
        textInput: TextInputLayout,
        textEdit: TextInputEditText,
        errorText: String
    ) {
        textInput.error = errorText
        textEdit.setBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.rounder_background_error,
                null
            )
        )
    }

    private fun hideError(
        textInput: TextInputLayout,
        textEdit: TextInputEditText
    ){
        textInput.error = null
        textEdit.setBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.rounder_background,
                null
            )
        )
    }

    private fun setPhoneMaskOnEditText() {
        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        mask.isHideHardcodedHead = true
        val formatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(binding.etPhoneNumber)
    }

    private fun configureStatusBar() {
        val window = requireActivity().window
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        requireActivity().actionBar?.show()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = resources.getColor(R.color.white, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}