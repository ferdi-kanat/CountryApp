package com.ferdikanat.countryapp.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ferdikanat.countryapp.R
import com.ferdikanat.countryapp.databinding.FragmentDetailBinding
import com.ferdikanat.countryapp.util.ApplicationViewModelFactory
import com.ferdikanat.countryapp.util.downloadURL
import com.ferdikanat.countryapp.viewmodel.MainViewModel


class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private val viewModel : MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container, false)
        viewModel.findByName(args.country.name)
        initUI()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initUI() {
        //val country = args.country
        viewModel.country.observe(viewLifecycleOwner) { country ->
            with(binding) {
                tvCountryName.text = country.name
                tvCapital.text = country.capital
                tvRegion.text = country.region
                tvLanguage.text = country.language
                ivFlag.downloadURL(country.flagUrl)
            }
        }
    }

}