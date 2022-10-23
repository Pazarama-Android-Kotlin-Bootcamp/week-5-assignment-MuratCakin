package com.muratcakin.userposts.ui.comments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.muratcakin.userposts.data.model.CommentDTO
import com.muratcakin.userposts.data.model.DataState
import com.muratcakin.userposts.databinding.FragmentCommentsBinding
import com.muratcakin.userposts.ui.comments.adapter.CommentsAdapter
import com.muratcakin.userposts.ui.comments.adapter.OnCommentClickListener
import com.muratcakin.userposts.ui.comments.viewmodel.CommentsViewModel
import com.muratcakin.userposts.ui.loadingprogress.LoadingProgressBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CommentsFragment : Fragment(), OnCommentClickListener {
    private lateinit var binding: FragmentCommentsBinding
    private lateinit var navController: NavController
    private val viewModel by viewModels<CommentsViewModel>()
    lateinit var loadingProgressBar: LoadingProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCommentsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingProgressBar = LoadingProgressBar(requireContext())
        navController = findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.commentLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData ->
                        binding.rvCommentsList.adapter = CommentsAdapter(this@CommentsFragment,this@CommentsFragment).apply {
                            submitList(safeData)
                        }
                    } ?: run {
                        Toast.makeText(requireContext(), "No data", Toast.LENGTH_SHORT).show()
                    }
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }
        }
    }

    override fun onCommentClick(comment: CommentDTO) {
        viewModel.onFavoriteComment(comment)
    }
}

