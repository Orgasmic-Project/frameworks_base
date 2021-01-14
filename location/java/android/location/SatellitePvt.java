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

package android.location;

import android.annotation.FloatRange;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * A class that contains GNSS satellite position, velocity and time information at the
 * signal transmission time {@link GnssMeasurement#getReceivedSvTimeNanos()}.
 *
 * @hide
 */
@SystemApi
public final class SatellitePvt implements Parcelable {
    private final PositionEcef mPositionEcef;
    private final VelocityEcef mVelocityEcef;
    private final ClockInfo mClockInfo;
    private final double mIonoDelayMeters;
    private final double mTropoDelayMeters;

    /**
     * Class containing estimates of the satellite position fields in ECEF coordinate frame.
     */
    public static final class PositionEcef implements Parcelable {
        private final double mXMeters;
        private final double mYMeters;
        private final double mZMeters;
        private final double mUreMeters;

        public PositionEcef(
                double xMeters,
                double yMeters,
                double zMeters,
                double ureMeters) {
            mXMeters = xMeters;
            mYMeters = yMeters;
            mZMeters = zMeters;
            mUreMeters = ureMeters;
        }

        public static final @NonNull Creator<PositionEcef> CREATOR =
                new Creator<PositionEcef>() {
                    @Override
                    public PositionEcef createFromParcel(Parcel in) {
                        return new PositionEcef(
                                in.readDouble(),
                                in.readDouble(),
                                in.readDouble(),
                                in.readDouble()
                        );
                    }

                    @Override
                    public PositionEcef[] newArray(int size) {
                        return new PositionEcef[size];
                    }
                };

        /**
         * Returns the satellite position X in WGS84 ECEF (meters).
         */
        @FloatRange()
        public double getXMeters() {
            return mXMeters;
        }

        /**
         * Returns the satellite position Y in WGS84 ECEF (meters).
         */
        @FloatRange()
        public double getYMeters() {
            return mYMeters;
        }

        /**
         * Returns the satellite position Z in WGS84 ECEF (meters).
         */
        @FloatRange()
        public double getZMeters() {
            return mZMeters;
        }

        /**
         * Returns the signal in Space User Range Error (URE) (meters).
         */
        @FloatRange(from = 0.0f, fromInclusive = false)
        public double getUreMeters() {
            return mUreMeters;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeDouble(mXMeters);
            dest.writeDouble(mYMeters);
            dest.writeDouble(mZMeters);
            dest.writeDouble(mUreMeters);
        }

        @Override
        public String toString() {
            return "PositionEcef{"
                    + "xMeters=" + mXMeters
                    + ", yMeters=" + mYMeters
                    + ", zMeters=" + mZMeters
                    + ", ureMeters=" + mUreMeters
                    + "}";
        }
    }

    /**
     * Class containing estimates of the satellite velocity fields in the ECEF coordinate frame.
     */
    public static final class VelocityEcef implements Parcelable {
        private final double mXMetersPerSecond;
        private final double mYMetersPerSecond;
        private final double mZMetersPerSecond;
        private final double mUreRateMetersPerSecond;

        public VelocityEcef(
                double xMetersPerSecond,
                double yMetersPerSecond,
                double zMetersPerSecond,
                double ureRateMetersPerSecond) {
            mXMetersPerSecond = xMetersPerSecond;
            mYMetersPerSecond = yMetersPerSecond;
            mZMetersPerSecond = zMetersPerSecond;
            mUreRateMetersPerSecond = ureRateMetersPerSecond;
        }

        public static final @NonNull Creator<VelocityEcef> CREATOR =
                new Creator<VelocityEcef>() {
                    @Override
                    public VelocityEcef createFromParcel(Parcel in) {
                        return new VelocityEcef(
                                in.readDouble(),
                                in.readDouble(),
                                in.readDouble(),
                                in.readDouble()
                        );
                    }

                    @Override
                    public VelocityEcef[] newArray(int size) {
                        return new VelocityEcef[size];
                    }
                };

        /**
         * Returns the satellite velocity X in WGS84 ECEF (meters per second).
         */
        @FloatRange()
        public double getXMetersPerSecond() {
            return mXMetersPerSecond;
        }

        /**
         * Returns the satellite velocity Y in WGS84 ECEF (meters per second).
         */
        @FloatRange()
        public double getYMetersPerSecond() {
            return mYMetersPerSecond;
        }

        /**
         *Returns the satellite velocity Z in WGS84 ECEF (meters per second).
         */
        @FloatRange()
        public double getZMetersPerSecond() {
            return mZMetersPerSecond;
        }

        /**
         * Returns the signal in Space User Range Error Rate (URE Rate) (meters per second).
         *
         * It covers satellite velocity error and Satellite clock drift
         * projected to the pseudorange rate measurements.
         */
        @FloatRange(from = 0.0f, fromInclusive = false)
        public double getUreRateMetersPerSecond() {
            return mUreRateMetersPerSecond;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeDouble(mXMetersPerSecond);
            dest.writeDouble(mYMetersPerSecond);
            dest.writeDouble(mZMetersPerSecond);
            dest.writeDouble(mUreRateMetersPerSecond);
        }

        @Override
        public String toString() {
            return "VelocityEcef{"
                    + "xMetersPerSecond=" + mXMetersPerSecond
                    + ", yMetersPerSecond=" + mYMetersPerSecond
                    + ", zMetersPerSecond=" + mZMetersPerSecond
                    + ", ureRateMetersPerSecond=" + mUreRateMetersPerSecond
                    + "}";
        }
    }

    /**
     * Class containing estimates of the satellite clock info.
     */
    public static final class ClockInfo implements Parcelable {
        private final double mHardwareCodeBiasMeters;
        private final double mTimeCorrectionMeters;
        private final double mClockDriftMetersPerSecond;

        public ClockInfo(
                double hardwareCodeBiasMeters,
                double timeCorrectionMeters,
                double clockDriftMetersPerSecond) {
            mHardwareCodeBiasMeters = hardwareCodeBiasMeters;
            mTimeCorrectionMeters = timeCorrectionMeters;
            mClockDriftMetersPerSecond = clockDriftMetersPerSecond;
        }

        public static final @NonNull Creator<ClockInfo> CREATOR =
                new Creator<ClockInfo>() {
                    @Override
                    public ClockInfo createFromParcel(Parcel in) {
                        return new ClockInfo(
                                in.readDouble(),
                                in.readDouble(),
                                in.readDouble()
                        );
                    }

                    @Override
                    public ClockInfo[] newArray(int size) {
                        return new ClockInfo[size];
                    }
                };

        /**
         * Returns the satellite hardware code bias of the reported code type w.r.t
         * ionosphere-free measurement in meters.
         */
        @FloatRange()
        public double getHardwareCodeBiasMeters() {
            return mHardwareCodeBiasMeters;
        }

        /**
         * Returns the satellite time correction for ionospheric-free signal measurement
         * (meters). The satellite clock correction for the given signal type
         * = satTimeCorrectionMeters - satHardwareCodeBiasMeters.
         */
        @FloatRange()
        public double getTimeCorrectionMeters() {
            return mTimeCorrectionMeters;
        }

        /**
         * Returns the satellite clock drift (meters per second).
         */
        @FloatRange()
        public double getClockDriftMetersPerSecond() {
            return mClockDriftMetersPerSecond;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeDouble(mHardwareCodeBiasMeters);
            dest.writeDouble(mTimeCorrectionMeters);
            dest.writeDouble(mClockDriftMetersPerSecond);
        }

        @Override
        public String toString() {
            return "ClockInfo{"
                    + "hardwareCodeBiasMeters=" + mHardwareCodeBiasMeters
                    + ", timeCorrectionMeters=" + mTimeCorrectionMeters
                    + ", clockDriftMetersPerSecond=" + mClockDriftMetersPerSecond
                    + "}";
        }
    }

    private SatellitePvt(
            @NonNull PositionEcef positionEcef,
            @NonNull VelocityEcef velocityEcef,
            @NonNull ClockInfo clockInfo,
            double ionoDelayMeters,
            double tropoDelayMeters) {
        if (positionEcef == null) {
            throw new IllegalArgumentException("Position Ecef cannot be null.");
        }
        if (velocityEcef == null) {
            throw new IllegalArgumentException("Velocity Ecef cannot be null.");
        }
        if (clockInfo == null) {
            throw new IllegalArgumentException("Clock Info cannot be null.");
        }
        mPositionEcef = positionEcef;
        mVelocityEcef = velocityEcef;
        mClockInfo = clockInfo;
        mIonoDelayMeters = ionoDelayMeters;
        mTropoDelayMeters = tropoDelayMeters;
    }

    /**
     * Returns a {@link PositionEcef} object that contains estimates of the satellite
     * position fields in ECEF coordinate frame.
     */
    @NonNull
    public PositionEcef getPositionEcef() {
        return mPositionEcef;
    }

    /**
     * Returns a {@link VelocityEcef} object that contains estimates of the satellite
     * velocity fields in the ECEF coordinate frame.
     */
    @NonNull
    public VelocityEcef getVelocityEcef() {
        return mVelocityEcef;
    }

    /**
     * Returns a {@link ClockInfo} object that contains estimates of the satellite
     * clock info.
     */
    @NonNull
    public ClockInfo getClockInfo() {
        return mClockInfo;
    }

    /**
     * Returns the ionospheric delay in meters.
     */
    @FloatRange()
    public double getIonoDelayMeters() {
        return mIonoDelayMeters;
    }

    /**
     * Returns the tropospheric delay in meters.
     */
    @FloatRange()
    public double getTropoDelayMeters() {
        return mTropoDelayMeters;
    }

    public static final @android.annotation.NonNull Creator<SatellitePvt> CREATOR =
            new Creator<SatellitePvt>() {
                @Override
                @Nullable
                public SatellitePvt createFromParcel(Parcel in) {
                    ClassLoader classLoader = getClass().getClassLoader();
                    PositionEcef positionEcef = in.readParcelable(classLoader);
                    VelocityEcef velocityEcef = in.readParcelable(classLoader);
                    ClockInfo clockInfo = in.readParcelable(classLoader);
                    double ionoDelayMeters = in.readDouble();
                    double tropoDelayMeters = in.readDouble();

                    return new SatellitePvt(
                            positionEcef,
                            velocityEcef,
                            clockInfo,
                            ionoDelayMeters,
                            tropoDelayMeters);
                }

                @Override
                public SatellitePvt[] newArray(int size) {
                    return new SatellitePvt[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeParcelable(mPositionEcef, flags);
        parcel.writeParcelable(mVelocityEcef, flags);
        parcel.writeParcelable(mClockInfo, flags);
        parcel.writeDouble(mIonoDelayMeters);
        parcel.writeDouble(mTropoDelayMeters);
    }

    @Override
    public String toString() {
        return "SatellitePvt{"
                + "PositionEcef=" + mPositionEcef
                + ", VelocityEcef=" + mVelocityEcef
                + ", ClockInfo=" + mClockInfo
                + ", IonoDelayMeters=" + mIonoDelayMeters
                + ", TropoDelayMeters=" + mTropoDelayMeters
                + "}";
    }

    /**
     * Builder class for SatellitePvt.
     */
    public static final class Builder {
        private PositionEcef mPositionEcef;
        private VelocityEcef mVelocityEcef;
        private ClockInfo mClockInfo;
        private double mIonoDelayMeters;
        private double mTropoDelayMeters;

        /**
         * Set position ECEF.
         *
         * @param positionEcef position ECEF object
         * @return Builder builder object
         */
        @NonNull
        public Builder setPositionEcef(
                @NonNull PositionEcef positionEcef) {
            mPositionEcef = positionEcef;
            return this;
        }

        /**
         * Set velocity ECEF.
         *
         * @param velocityEcef velocity ECEF object
         * @return Builder builder object
         */
        @NonNull
        public Builder setVelocityEcef(
                @NonNull VelocityEcef velocityEcef) {
            mVelocityEcef = velocityEcef;
            return this;
        }

        /**
         * Set clock info.
         *
         * @param clockInfo clock info object
         * @return Builder builder object
         */
        @NonNull
        public Builder setClockInfo(
                @NonNull ClockInfo clockInfo) {
            mClockInfo = clockInfo;
            return this;
        }

        /**
         * Set ionospheric delay in meters.
         *
         * @param ionoDelayMeters ionospheric delay (meters)
         * @return Builder builder object
         */
        @NonNull
        public Builder setIonoDelayMeters(@FloatRange() double ionoDelayMeters) {
            mIonoDelayMeters = ionoDelayMeters;
            return this;
        }

        /**
         * Set tropospheric delay in meters.
         *
         * @param tropoDelayMeters tropospheric delay (meters)
         * @return Builder builder object
         */
        @NonNull
        public Builder setTropoDelayMeters(@FloatRange() double tropoDelayMeters) {
            mTropoDelayMeters = tropoDelayMeters;
            return this;
        }

        /**
         * Build SatellitePvt object.
         *
         * @return instance of SatellitePvt
         */
        @NonNull
        public SatellitePvt build() {
            return new SatellitePvt(mPositionEcef, mVelocityEcef, mClockInfo,
                    mIonoDelayMeters, mTropoDelayMeters);
        }
    }
}
