package com.gilboot.agriculturemarket.addproduce


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.gilboot.agriculturemarket.*
import com.gilboot.agriculturemarket.databinding.ActivityAddProduceBinding
import com.theartofdev.edmodo.cropper.CropImage
import timber.log.Timber

/**
 * Activity responsible for registering new users.
 */
class AddProduceActivity : AppCompatActivity() {
    val addProduceViewModel: AddProduceViewModel by viewModels {
        AddProduceViewModelFactory(repository)
    }

    lateinit var binding: ActivityAddProduceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_add_produce
            )

        binding.addProduceViewModel = addProduceViewModel
        binding.lifecycleOwner = this

        binding.card.setOnClickListener { startChooseImage() }
        binding.btnAdd.setOnClickListener { addProduce() }
        setObservers()
    }

    /**
     * Processes image uri returned from an intent
     * The image view is set to show the image returned
     * Kicks of text recognition for the image via processImage function
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return
        when (requestCode) {
            /**
             * Use Uri obtained from cropping an image
             */
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                val uri = CropImage.getActivityResult(data).uri
                processUri(uri)
                Timber.i("crop uri is $uri")
            }
            /**
             * Get uri obtained from choosing an image and send it to the cropping intent
             */
            CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE -> {
//                val resultUri = result.uri
                //From here you can load the image however you need to, I recommend using the Glide library
//                val uri = CropImage.getCaptureImageOutputUri(requireContext())
                val uri = CropImage.getPickImageResultUri(this, data)
                Timber.i("Pick uri is $uri")

                // For API >= 23 we need to check specifically that we have permissions to read external storage.
                if (CropImage.isReadExternalStoragePermissionsRequired(this, uri)) {
                    // request permissions and handle the result in onRequestPermissionsResult()
                    mCropImageUri = uri
                    requestReadPermissions()

                } else {
                    // no permissions required or already granted, can start crop image activity
                    startCropImageActivity(uri)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
            if (mCropImageUri != null && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // required permissions granted, start crop image activity
                startCropImageActivity(mCropImageUri!!)
            } else {
                longSnackbar("Cancelling, required permissions are not granted")
            }
        }
    }

    companion object {
        var mCropImageUri: Uri? = null
    }


}


/**
 * Starts activity for choosing an image from the phone and returns uri to the image
 */
fun AddProduceActivity.startChooseImage() {
    val chooseIntent = CropImage.getPickImageChooserIntent(this, "Produce Picture", true, true)
    startActivityForResult(
        chooseIntent,
        CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
    )
}

/**
 * Starts activity for cropping image
 * Using an intent with requestCode CROP_IMAGE_ACTIVITY_REQUEST_CODE
 */
fun AddProduceActivity.startCropImageActivity(imageUri: Uri) {
    CropImage.activity(imageUri)
        .setAspectRatio(IMAGE_ASPECT_RATIO_X_IN_MM, IMAGE_ASPECT_RATIO_Y_IN_MM)
        .setFixAspectRatio(true)
        .start(this)
}

/**
 * We use the uri to obtained the text in the image, save it and attempt to finish the PictureFragment
 */
fun AddProduceActivity.processUri(imageUri: Uri) {
//    longToast("Uri is $imageUri")
    addProduceViewModel.setPicture(imageUri)
}

/**
 * Add Produce to the produce collection and finish activity with result of produceId
 */
fun AddProduceActivity.addProduce() {
    binding.apply {
        when {
            name.isNotValid() -> longSnackbar("Invalid Name")
            price.isNotValid() -> longSnackbar("Invalid Price")
            unit.isNotValid() -> longSnackbar("Invalid Unit")
            dateOfAvailability.isNotValid() -> longSnackbar("Invalid date of availability")
            addProduceViewModel?.pictureUriLiveData?.value == null -> longSnackbar("Picture not set")
            else -> {
                addProduceViewModel?.addProduce(
                    name.text.toString(),
                    price.text.toString().toInt(),
                    unit.text.toString(),
                    dateOfAvailability.text.toString()
                )
            }
        }
    }
}

/**
 * Observe snack message
 */
fun AddProduceActivity.observeSnackMessage() {
    addProduceViewModel.snackMessageLiveData.observe(this, Observer {
        it?.let { event ->
            event.getContentIfNotHandledOrReturnNull()?.let { msg ->
                longSnackbar(msg)
            }
        }
    })
}

fun AddProduceActivity.observeFinish() {
    addProduceViewModel.finishLiveData.observe(this, Observer { produceId ->
        produceId?.let {
            // return image uri
            val returnIntent = Intent().apply {
                putExtra("produceId", produceId)
            }
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    })
}

/**
 * For devices running Marshmallow we may need to
 * we may need to get read external storage permissions
 */
fun Activity.requestReadPermissions() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE
        )
    }
}


/**
 * call observer functions we have
 */
fun AddProduceActivity.setObservers() {
    observeSnackMessage()
    observeFinish()
}