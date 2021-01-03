package com.gilboot.agriculturemarket.produce


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gilboot.agriculturemarket.R
import com.gilboot.agriculturemarket.databinding.ProduceListBinding
import com.gilboot.agriculturemarket.repository


class ProduceListFragment : Fragment() {
    val produceViewModel: ProduceViewModel by activityViewModels {
        ProduceViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding =
            DataBindingUtil.inflate<ProduceListBinding>(
                inflater,
                R.layout.produce_list,
                container,
                false
            )

        binding.produceViewModel = produceViewModel
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            produceList.adapter = ProduceListAdapter(produceOnClickListener)
        }

        return binding.root
    }
}

val ProduceListFragment.produceOnClickListener: ProduceListAdapter.OnClickListener
    get() = ProduceListAdapter.OnClickListener {
        produceViewModel.setCurrentProduce(it)
        navigateToProduceDetail()
    }

fun ProduceListFragment.navigateToProduceDetail() {
    findNavController().navigate(
        ProduceListFragmentDirections.actionProduceListFragmentToProduceDetailFragment()
    )
}

