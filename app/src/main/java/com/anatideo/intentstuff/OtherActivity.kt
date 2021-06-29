package com.anatideo.intentstuff

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.anatideo.intentstuff.databinding.ActivityOtherBinding
import com.anatideo.model.Song

class OtherActivity : AppCompatActivity() {

    private val binding by lazy { ActivityOtherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.rootView)

        // Get values from intent
        val stringFromIntent = intent.extras?.getString(EXTRA_STRING_KEY)
        val booleanFromIntent = intent.extras?.getBoolean(EXTRA_BOOLEAN_KEY)
        val intFromIntent = intent.extras?.getInt(EXTRA_INT_KEY)
        val listFromIntent = intent.extras?.getParcelableArrayList<Song>(EXTRA_LIST_KEY)?.toList()
        val firstIndexListFromIntent = intent.extras?.getParcelable<Song>(EXTRA_FIRST_INDEX_LIST_KEY)

        // Then show it
        binding.stringValue.text = stringFromIntent
        binding.booleanValue.text = booleanFromIntent.toString()
        binding.intValue.text = intFromIntent.toString()
        binding.listValue.text = listFromIntent.toString()
        binding.firstIndexListValue.text = firstIndexListFromIntent?.name.toString()
    }

    companion object {
        const val EXTRA_STRING_KEY = "EXTRA_STRING_KEY"
        const val EXTRA_BOOLEAN_KEY = "EXTRA_BOOLEAN_KEY"
        const val EXTRA_INT_KEY = "EXTRA_INT_KEY"
        const val EXTRA_LIST_KEY = "EXTRA_LIST_KEY"
        const val EXTRA_FIRST_INDEX_LIST_KEY = "EXTRA_FIRST_INDEX_LIST_KEY"

        fun newIntent(
            context: Context,
            stringValue: String,
            booleanValue: Boolean,
            intValue: Int,
            listValue: List<Song>,
            firstIndexListValue: Song
        ): Intent {
            val intent =  Intent(context, OtherActivity::class.java)

            intent.putExtra(EXTRA_STRING_KEY, stringValue)
            intent.putExtra(EXTRA_BOOLEAN_KEY, booleanValue)
            intent.putExtra(EXTRA_INT_KEY, intValue)
            intent.putParcelableArrayListExtra(EXTRA_LIST_KEY, ArrayList(listValue))
            intent.putExtra(EXTRA_FIRST_INDEX_LIST_KEY, firstIndexListValue)

            return intent
        }
    }
}