package com.smartattendance.qrapp.util

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.smartattendance.qrapp.data.model.QRPayload
import org.json.JSONObject

object QRUtils {

    /**
     * Serializes a QRPayload to a compact JSON string for embedding in QR.
     */
    fun encodePayload(payload: QRPayload): String {
        return JSONObject().apply {
            put("sid", payload.sessionId)
            put("cid", payload.classId)
            put("cn", payload.className)
            put("sub", payload.subject)
            put("tuid", payload.teacherUid)
            put("cat", payload.createdAt)
            put("exp", payload.expiresAt)
        }.toString()
    }

    /**
     * Deserializes a JSON string back into QRPayload.
     */
    fun decodePayload(json: String): QRPayload? {
        return try {
            val obj = JSONObject(json)
            QRPayload(
                sessionId = obj.getString("sid"),
                classId = obj.getString("cid"),
                className = obj.getString("cn"),
                subject = obj.getString("sub"),
                teacherUid = obj.getString("tuid"),
                createdAt = obj.getLong("cat"),
                expiresAt = obj.getLong("exp")
            )
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Generates a QR code Bitmap from a string payload.
     */
    fun generateQRBitmap(content: String, size: Int = 800): Bitmap? {
        return try {
            val hints = mapOf(
                EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.H,
                EncodeHintType.MARGIN to 1,
                EncodeHintType.CHARACTER_SET to "UTF-8"
            )
            val writer = QRCodeWriter()
            val bitMatrix: BitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, size, size, hints)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) 0xFF1A1A2E.toInt() else 0xFFFFFFFF.toInt())
                }
            }
            bmp
        } catch (e: WriterException) {
            null
        }
    }

    /**
     * Checks whether a QR payload has expired.
     */
    fun isExpired(payload: QRPayload): Boolean {
        return System.currentTimeMillis() > payload.expiresAt
    }

    /**
     * Returns remaining time in seconds, or 0 if expired.
     */
    fun remainingSeconds(payload: QRPayload): Long {
        val remaining = (payload.expiresAt - System.currentTimeMillis()) / 1000L
        return if (remaining < 0) 0L else remaining
    }
}
