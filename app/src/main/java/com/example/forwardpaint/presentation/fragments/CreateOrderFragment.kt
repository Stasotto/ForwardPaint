package com.example.forwardpaint.presentation.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.FragmentCreateOrderBinding
import com.example.forwardpaint.presentation.models.Order
import com.example.forwardpaint.presentation.viewmodels.MainFragViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.ByteArrayOutputStream

class CreateOrderFragment : Fragment(R.layout.fragment_create_order) {

    companion object {
        const val TAG = "Create Order"
        fun newInstance() = CreateOrderFragment()
    }

    private val binding by viewBinding(FragmentCreateOrderBinding::bind)
    private var launcher: ActivityResultLauncher<Intent>? = null
    private val vieModel by sharedViewModel<MainFragViewModel>()
    private var im: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermissions()
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val bitmap = result.data?.extras?.get("data") as Bitmap?
                val baos = ByteArrayOutputStream()
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                im = baos.toByteArray()
                binding.image.setImageBitmap(bitmap)
                binding.imageText.text =
                    Editable.Factory.getInstance().newEditable(bitmap.toString())
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageText.setOnClickListener {
            openCamera()
        }
        binding.btnCreateOrder.setOnClickListener {
            vieModel.saveOrder(createOrder(), im!!)
        }
    }

    private fun createOrder(): Order = with(binding) {
        return Order(
            name = nameText.text.toString(),
            lastName = lastNameText.text.toString(),
            phoneNumber = phoneNumberText.text.toString().toLong(),
            numberOfOrder = numberOfOrderText.text.toString().toInt(),
            typeOfOrder = typeOfOrderText.text.toString(),
            price = priceText.text.toString().toInt(),
            image = null,
            status = "Принят"
        )
    }

    private fun openCamera() {
        launcher?.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }


    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CAMERA
            )
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                101
            )
        }
    }
}