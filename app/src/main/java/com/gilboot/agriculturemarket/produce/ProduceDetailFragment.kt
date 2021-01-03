package com.gilboot.agriculturemarket.produce


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gilboot.agriculturemarket.R
import com.gilboot.agriculturemarket.databinding.ProduceDetailBinding
import com.gilboot.agriculturemarket.repository

class ProduceDetailFragment : Fragment() {
    private val produceViewModel: ProduceViewModel by activityViewModels {
        ProduceViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding =
            DataBindingUtil.inflate<ProduceDetailBinding>(
                inflater,
                R.layout.produce_detail,
                container,
                false
            )

        binding.produceViewModel = produceViewModel
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }
}
