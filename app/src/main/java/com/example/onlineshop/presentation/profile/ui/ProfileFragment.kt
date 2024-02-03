package com.example.onlineshop.presentation.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlineshop.databinding.FragmentProfileBinding
import com.example.onlineshop.domain.models.User
import com.example.onlineshop.presentation.profile.view_model.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsersData()
        viewModel.userData.observe(viewLifecycleOwner){user->
            bindUsersInfo(user)
        }
        viewModel.getFavoritesNumber()
        viewModel.favoritesNumber.observe(viewLifecycleOwner){number->
            manageFavoriteView(number)
        }

        binding.btnExit.setOnClickListener {
            viewModel.clearAllData()
            val action = ProfileFragmentDirections.actionProfileFragmentToRegistrationFragment()
            findNavController().navigate(action)
        }

        binding.llFavorite.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToFavoriteFragment()
            findNavController().navigate(action)
        }
    }

    private fun bindUsersInfo(user: User){
        val fullName = "${user.firstName} ${user.lastName}"
        binding.tvUserName.text = fullName
        binding.tvUserPhone.text = user.phoneNumber
    }

    private fun manageFavoriteView(number: Int){
        if(number>0){
            binding.tvFavoriteNumber.visibility = View.VISIBLE
            binding.spacerFavoriteBottom.visibility = View.GONE
            binding.spacerFavoriteTop.visibility = View.GONE
            binding.tvFavoriteNumber.text = getRightEndingFavorite(number)
        }
        else{
            binding.spacerFavoriteBottom.visibility = View.VISIBLE
            binding.spacerFavoriteTop.visibility = View.VISIBLE
            binding.tvFavoriteNumber.visibility = View.GONE
        }
    }

    private fun getRightEndingFavorite(number: Int): String {
        val preLastDigit = number % 100 / 10

        if (preLastDigit == 1) {
            return "$number товаров"
        }

        return when (number % 10) {
            1 -> "$number товар"
            2 -> "$number товара"
            3 -> "$number товара"
            4 -> "$number товара"
            else -> "$number товаров"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}