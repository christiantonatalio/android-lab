package com.christianto.natalio.android.lab.screens.favorite

import com.christianto.natalio.android.lab.model.Favorite

sealed class FavoriteState{
    data class Add(val favorite: Favorite) : FavoriteState()
    data class Delete(val favorite: Favorite) : FavoriteState()
}