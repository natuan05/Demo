// Top-level build file...
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false // <-- THÊM DÒNG NÀY
    id("com.google.gms.google-services") version "4.4.4" apply false
}
// XÓA DẤU "}" THỪA Ở ĐÂY