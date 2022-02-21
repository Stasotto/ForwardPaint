package com.example.forwardpaint.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.FragmentMainBinding
import com.example.forwardpaint.presentation.models.Order
import com.example.forwardpaint.presentation.recyclerOrder.AdapterOrder
import com.example.forwardpaint.presentation.recyclerOrder.OnItemClickListener
import com.example.forwardpaint.presentation.viewmodels.MainFragViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        const val TAG = "Main"

        fun newInstance() = MainFragment()
    }

    private val onItemClickListener: OnItemClickListener = object : OnItemClickListener {
        override fun setOnItemClickListener(order: Order) {
            openDescriptionFrag(order)
        }
    }

    private val binding by viewBinding<FragmentMainBinding> { }
    private val adapter: AdapterOrder by lazy { AdapterOrder(onItemClickListener) }
    private val viewModel by sharedViewModel<MainFragViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        binding.createNewOrder.setOnClickListener {
            openCreateOderFrag()
        }
    }

    private fun setUpRecycler() = with(binding) {
        recyclerOrder.adapter = adapter
        recyclerOrder.layoutManager = LinearLayoutManager(requireContext())

        viewModel.orderForRecycler.observe(viewLifecycleOwner, { orderList ->
            adapter.addOrders(orderList)
        })

        viewModel.order2.observe(viewLifecycleOwner, {
            adapter.addOrder(it)
        })
    }

    private fun openCreateOderFrag() {
        parentFragmentManager.beginTransaction()
            .addToBackStack(CreateOrderFragment.TAG)
            .replace(R.id.containerFrag, CreateOrderFragment.newInstance(), CreateOrderFragment.TAG)
            .commit()
    }

    private fun openDescriptionFrag(item: Order) {
        parentFragmentManager.beginTransaction()
            .addToBackStack(DescriptionFragment.TAG)
            .replace(
                R.id.containerFrag,
                DescriptionFragment.newInstance(item),
                DescriptionFragment.TAG
            )
            .commit()
    }
}