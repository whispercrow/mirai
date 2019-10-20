package net.mamoe.mirai.utils

import kotlinx.io.core.ByteReadPacket
import net.mamoe.mirai.message.ImageId

class PlatformImage(
        val width: Int,
        val height: Int,
        val md5: ByteArray,
        val format: String,
        val fileData: ByteReadPacket
) {
    val fileSize: Long = fileData.remaining

    val id: ImageId by lazy { ImageId("{${md5[0..3]}-${md5[4..5]}-${md5[6..7]}-${md5[8..9]}-${md5[10..15]}}.$format") }

    override fun toString(): String = "[PlatformImage(${width}x${height} $format)]"
}

private operator fun ByteArray.get(range: IntRange): String = buildString {
    range.forEach {
        append(this@get[it].toUHexString())
    }
}

expect val PlatformImage.imageWidth: Int

expect val PlatformImage.imageHeight: Int