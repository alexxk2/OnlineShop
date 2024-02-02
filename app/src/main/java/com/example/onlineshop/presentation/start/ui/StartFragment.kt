package com.example.onlineshop.presentation.start.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlineshop.databinding.FragmentStartBinding
import com.example.onlineshop.presentation.start.view_model.StartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLoginStatus()
        viewModel.isUserLoggedIn.observe(viewLifecycleOwner){
            navigate(it)
        }
    }

    private fun navigate(isUserLoggedIn: Boolean){
        if (isUserLoggedIn){
            val action = StartFragmentDirections.actionStartFragmentToCatalogFragment()
            findNavController().navigate(action)
        }
        else{
            val action = StartFragmentDirections.actionStartFragmentToRegistrationFragment()
            findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}