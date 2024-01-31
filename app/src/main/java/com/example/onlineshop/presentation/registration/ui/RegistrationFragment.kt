package com.example.onlineshop.presentation.registration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlineshop.databinding.FragmentRegistrationBinding
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPhoneMaskOnEditText()
    }

    private fun setPhoneMaskOnEditText() {
        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        mask.isHideHardcodedHead = true
        val formatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(binding.etPhoneNumber)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}