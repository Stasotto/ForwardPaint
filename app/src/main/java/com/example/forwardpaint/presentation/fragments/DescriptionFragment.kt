package com.example.forwardpaint.presentation.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.InputType
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.FragmentDescriptionBinding
import com.example.forwardpaint.presentation.models.Order
import com.example.forwardpaint.presentation.viewmodels.MainFragViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.ByteArrayOutputStream

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
    private var launcher: ActivityResultLauncher<Intent>? = null
    private val viewModel by sharedViewModel<MainFragViewModel>()
    private val listEditText by lazy {
        with(binding) {
            mutableListOf(
                nameText,
                lastNameText,
                phoneNumberText,
                numberOfOrderText,
                typeOfOrderText,
                priceText,
                statusText,
                imageText
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        setData()

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val bitmap = result.data?.extras?.get("data") as Bitmap?
                binding.photo.setImageBitmap(bitmap)
                binding.imageText.text =
                    Editable.Factory.getInstance().newEditable(bitmap.toString())
            }
    }

    private fun getImageByteArray(): ByteArray {
        val bitmap = binding.photo.drawable.toBitmap()
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }

    private fun setOnClickListeners() {
        binding.btnEditOrder.setOnClickListener {
            setEditMode()
            it.isVisible = false
            binding.btnSaveOrder.isVisible = true
            binding.edit.isVisible = true
        }
        binding.imageText.setOnClickListener {
            openCamera()

        }
        binding.btnSaveOrder.setOnClickListener {
            viewModel.saveOrder(createOrder(), getImageByteArray())
        }
    }

    private fun setData() = with(binding) {
        listEditText.forEach { editText ->
            editText.inputType = InputType.TYPE_NULL
            editText.setBackgroundColor(Color.TRANSPARENT)
        }
        nameText.text = toEditable(order?.name ?: "-")
        lastNameText.text = toEditable(order?.lastName ?: "-")
        phoneNumberText.text = toEditable(order?.phoneNumber.toString())
        numberOfOrderText.text = toEditable(order?.numberOfOrder.toString())
        typeOfOrderText.text = toEditable(order?.typeOfOrder ?: "-")
        priceText.text = toEditable(order?.price.toString())
        statusText.text = toEditable(order?.status ?: "-")
        imageText.text = toEditable(order?.image ?: "-")
        loadImage(order?.image ?: "")

    }

    private fun createOrder(): Order = with(binding) {
        return Order(
            name = nameText.text.toString(),
            lastName = lastNameText.text.toString(),
            phoneNumber = phoneNumberText.text.toString().toLong(),
            numberOfOrder = numberOfOrderText.text.toString().toInt(),
            typeOfOrder = typeOfOrderText.text.toString(),
            price = priceText.text.toString().toInt(),
            image = "",
            status = statusText.text.toString()
        )
    }

    private fun setEditMode() {
        listEditText.removeLast()
        listEditText.forEachIndexed { index, editText ->
            with(editText) {
                inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            }
        }
    }

    private fun loadImage(url: String) {
        Glide.with(binding.photo.context)
            .load(url)
            .placeholder(R.drawable.no_image)
            .into(binding.photo)
    }

    private fun toEditable(string: String) = Editable.Factory.getInstance().newEditable(string)

    private fun openCamera() {
        launcher?.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }
}