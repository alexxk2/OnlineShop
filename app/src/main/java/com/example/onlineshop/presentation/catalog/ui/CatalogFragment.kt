package com.example.onlineshop.presentation.catalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentCatalogBinding


class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSortingMenu()
        setChipFilter()


    }

    private fun setSortingMenu() {
        val listPopupWindow =
            ListPopupWindow(requireContext(), null, androidx.appcompat.R.attr.listPopupWindowStyle)
        listPopupWindow.anchorView = binding.llSorting
        val items = listOf(
            getString(R.string.sorting_popular),
            getString(R.string.sorting_price_from_high),
            getString(R.string.sorting_price_from_low)
        )
        val adapter = ArrayAdapter(requireContext(), R.layout.list_popup_window_item, items)
        listPopupWindow.setAdapter(adapter)
        listPopupWindow.background?.setTint(ContextCompat.getColor(requireContext(), R.color.white))
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            when (position) {
                0 -> {
                    binding.tvSorting.text = getString(R.string.sorting_popular)
                }

                1 -> {
                    binding.tvSorting.text = getString(R.string.sorting_price_from_high)
                }

                else -> {
                    binding.tvSorting.text = getString(R.string.sorting_price_from_low)
                }
            }

            listPopupWindow.dismiss()
        }

        binding.llSorting.setOnClickListener {
            listPopupWindow.show()
        }
    }


    private fun setChipFilter(){

        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->

            when(group.checkedChipId){
                binding.chipSeeAll.id-> {
                    manageChipCloseIcons(binding.chipSeeAll.id)
                }
                binding.chipFace.id -> {
                    manageChipCloseIcons(binding.chipFace.id)
                }
                binding.chipBody.id -> {
                    manageChipCloseIcons( binding.chipBody.id)
                }
                binding.chipSuntan.id -> {
                    manageChipCloseIcons(binding.chipSuntan.id)
                }
                binding.chipMassage.id -> {
                    manageChipCloseIcons(binding.chipMassage.id)
                }
                else -> {
                    manageChipCloseIcons(binding.chipLabel.id)
                }

            }

        }
    }

    private fun manageChipCloseIcons(position: Int){
        when(position){
            binding.chipSeeAll.id -> {
                binding.chipSeeAll.isCloseIconVisible = true
                binding.chipFace.isCloseIconVisible = false
                binding.chipBody.isCloseIconVisible = false
                binding.chipSuntan.isCloseIconVisible = false
                binding.chipMassage.isCloseIconVisible = false
                binding.chipLabel.isCloseIconVisible = false

                binding.chipSeeAll.setOnCloseIconClickListener {

                }
            }
            binding.chipFace.id -> {
                binding.chipSeeAll.isCloseIconVisible = false
                binding.chipFace.isCloseIconVisible = true
                binding.chipBody.isCloseIconVisible = false
                binding.chipSuntan.isCloseIconVisible = false
                binding.chipMassage.isCloseIconVisible = false
                binding.chipLabel.isCloseIconVisible = false

                binding.chipFace.setOnCloseIconClickListener {

                }
            }
            binding.chipBody.id -> {
                binding.chipSeeAll.isCloseIconVisible = false
                binding.chipFace.isCloseIconVisible = false
                binding.chipBody.isCloseIconVisible = true
                binding.chipSuntan.isCloseIconVisible = false
                binding.chipMassage.isCloseIconVisible = false
                binding.chipLabel.isCloseIconVisible = false

                binding.chipBody.setOnCloseIconClickListener {

                }
            }
            binding.chipSuntan.id -> {
                binding.chipSeeAll.isCloseIconVisible = false
                binding.chipFace.isCloseIconVisible = false
                binding.chipBody.isCloseIconVisible = false
                binding.chipSuntan.isCloseIconVisible = true
                binding.chipMassage.isCloseIconVisible = false
                binding.chipLabel.isCloseIconVisible = false

                binding.chipSuntan.setOnCloseIconClickListener {

                }
            }
            binding.chipMassage.id -> {
                binding.chipSeeAll.isCloseIconVisible = false
                binding.chipFace.isCloseIconVisible = false
                binding.chipBody.isCloseIconVisible = false
                binding.chipSuntan.isCloseIconVisible = false
                binding.chipMassage.isCloseIconVisible = true
                binding.chipLabel.isCloseIconVisible = false

                binding.chipMassage.setOnCloseIconClickListener {

                }
            }
            else -> {
                binding.chipSeeAll.isCloseIconVisible = false
                binding.chipFace.isCloseIconVisible = false
                binding.chipBody.isCloseIconVisible = false
                binding.chipSuntan.isCloseIconVisible = false
                binding.chipMassage.isCloseIconVisible = false
                binding.chipLabel.isCloseIconVisible = true

                binding.chipLabel.setOnCloseIconClickListener {

                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}