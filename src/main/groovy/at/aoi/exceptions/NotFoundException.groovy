package at.aoi.exceptions

/**
 * @author Gary Ye
 */
class NotFoundException extends Exception {
    NotFoundException() {
    }

    NotFoundException(String var1) {
        super(var1)
    }

    NotFoundException(String var1, Throwable var2) {
        super(var1, var2)
    }

    NotFoundException(Throwable var1) {
        super(var1)
    }

    NotFoundException(String var1, Throwable var2, boolean var3, boolean var4) {
        super(var1, var2, var3, var4)
    }
}
