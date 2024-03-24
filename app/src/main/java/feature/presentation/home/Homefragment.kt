package feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.berkah.swiftiesfood.databinding.FragmentHomefragmentBinding


class Homefragment : Fragment() {

    private lateinit var binding: FragmentHomefragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentHomefragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}