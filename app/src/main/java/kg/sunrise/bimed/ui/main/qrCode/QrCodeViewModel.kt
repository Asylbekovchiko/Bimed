package kg.sunrise.bimed.ui.main.qrCode

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kg.sunrise.bimed.base.viewModel.BaseViewModel
import net.glxn.qrgen.android.MatrixToImageWriter
import java.io.ByteArrayOutputStream

class QrCodeViewModel : BaseViewModel() {

    fun generateQrBitmap(qrCode: String, size: Int): Bitmap {
        val writer = QRCodeWriter()
        val bitMartix = writer.encode(qrCode, BarcodeFormat.QR_CODE, size, size)
        val stream = ByteArrayOutputStream()
        MatrixToImageWriter.writeToStream(bitMartix, "PNG", stream)
        val qr: ByteArray = stream.toByteArray()
        return BitmapFactory.decodeByteArray(qr, 0, qr.size)
    }
}