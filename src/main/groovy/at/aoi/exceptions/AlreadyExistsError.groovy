package at.aoi.exceptions

/**
 * @author Gary Ye
 */
class AlreadyExistsError extends Exception {
    AlreadyExistsError() {
    }

    AlreadyExistsError(String var1) {
        super(var1)
    }

    AlreadyExistsError(String var1, Throwable var2) {
        super(var1, var2)
    }

    AlreadyExistsError(Throwable var1) {
        super(var1)
    }

    AlreadyExistsError(String var1, Throwable var2, boolean var3, boolean var4) {
        super(var1, var2, var3, var4)
    }
}
