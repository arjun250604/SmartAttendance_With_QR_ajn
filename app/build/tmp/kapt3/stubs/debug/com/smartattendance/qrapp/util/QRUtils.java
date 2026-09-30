package com.smartattendance.qrapp.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004J\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0004\u00a8\u0006\u0012"}, d2 = {"Lcom/smartattendance/qrapp/util/QRUtils;", "", "()V", "decodePayload", "Lcom/smartattendance/qrapp/data/model/QRPayload;", "json", "", "encodePayload", "payload", "generateQRBitmap", "Landroid/graphics/Bitmap;", "content", "size", "", "isExpired", "", "remainingSeconds", "", "app_debug"})
public final class QRUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.smartattendance.qrapp.util.QRUtils INSTANCE = null;
    
    private QRUtils() {
        super();
    }
    
    /**
     * Serializes a QRPayload to a compact JSON string for embedding in QR.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String encodePayload(@org.jetbrains.annotations.NotNull()
    com.smartattendance.qrapp.data.model.QRPayload payload) {
        return null;
    }
    
    /**
     * Deserializes a JSON string back into QRPayload.
     */
    @org.jetbrains.annotations.Nullable()
    public final com.smartattendance.qrapp.data.model.QRPayload decodePayload(@org.jetbrains.annotations.NotNull()
    java.lang.String json) {
        return null;
    }
    
    /**
     * Generates a QR code Bitmap from a string payload.
     */
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap generateQRBitmap(@org.jetbrains.annotations.NotNull()
    java.lang.String content, int size) {
        return null;
    }
    
    /**
     * Checks whether a QR payload has expired.
     */
    public final boolean isExpired(@org.jetbrains.annotations.NotNull()
    com.smartattendance.qrapp.data.model.QRPayload payload) {
        return false;
    }
    
    /**
     * Returns remaining time in seconds, or 0 if expired.
     */
    public final long remainingSeconds(@org.jetbrains.annotations.NotNull()
    com.smartattendance.qrapp.data.model.QRPayload payload) {
        return 0L;
    }
}