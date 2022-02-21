package com.example.forwardpaint.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.FragmentDescriptionBinding
import com.example.forwardpaint.presentation.models.Order

class DescriptionFragment : Fragment(R.layout.fragment_description) {

    companion object {
        const val TAG = "Description"
        private const val ORDER_KEY = "Order key"

        fun newInstance(item: Order) = DescriptionFragment().apply {
            arguments = Bundle().apply { putParcelable(ORDER_KEY, item) }
        }
    }

    private val order: Order? by lazy { arguments?.getParcelable(ORDER_KEY) }
    private val binding by viewBinding(FragmentDescriptionBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() = with(binding) {
        nameText.text = order?.name
        lastNameText.text = order?.lastName
        phoneNumberText.text = order?.phoneNumber.toString()
        numberOfOrderText.text = order?.numberOfOrder.toString()
        typeOfOrderText.text = order?.typeOfOrder
        priceText.text = order?.price.toString()
        statusText.text = order?.status
    }
}