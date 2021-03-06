/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.server.pm.test

import java.nio.file.Path
import java.nio.file.Paths

// Unfortunately no easy way to access PMS SystemPartitions, so mock them here
internal enum class Partition(val baseAppFolder: Path) {
    SYSTEM("/system/app"),
    SYSTEM_PRIVILEGED("/system/priv-app"),
    VENDOR("/vendor/app"),
    PRODUCT("/product/app"),
    SYSTEM_EXT("/system_ext/app")
    ;

    constructor(baseFolder: String) : this(Paths.get(baseFolder))
}
