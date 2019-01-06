package org.ucll.ti.richfridge;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public class QrCodeViewModel extends AndroidViewModel {

    private Bitmap qrCode;

    public QrCodeViewModel(@NonNull Application application) {
        super(application);
    }

    public Bitmap getQrCode() {
        return qrCode;
    }

    public void setQrCode(Bitmap qrCode) {
        this.qrCode = qrCode;
    }
}
