package com.lnsantos.dojo.extension

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

internal fun MutableState<Boolean>.onInverterValue() : Unit {
    this.value = value.not()
}

internal fun MutableState<Boolean>.stringByState(isTrue: String, isFalse: String) : String {
    return if (this.value) isTrue else isFalse
}

internal fun MutableState<Boolean>.dpByState(isTrue: Float, isFalse: Float) : Dp {
    return Dp(if (this.value) isTrue else isFalse)
}

internal fun MutableState<Boolean>.iconExpandByState() : ImageVector {
    return if (this.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore
}

