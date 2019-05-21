package com.marcerlorbenites.followers.list

interface FollowerListView {
    fun showMainLoading()
    fun hideListLoading()
    fun hideFollowers()
    fun showListLoading()
    fun hideMainLoading()
    fun hideError()
    fun hideRetry()
    fun showFollowers(followerList: FollowerListViewModel)
    fun showNetworkError()
    fun showUnknownError()
    fun showRetry()
}
