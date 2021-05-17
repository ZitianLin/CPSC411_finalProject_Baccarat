package org.csuf.cpsc411.simplerestcoemt

import android.media.Image
import android.widget.ImageView
import kotlinx.serialization.Serializable

@Serializable
data class Card(var image : Int, var value : Int)
