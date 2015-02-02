package com.carlosdelachica.timelydigitalclock.model;

import com.carlosdelachica.timelydigitalclock.model.number.Eight;
import com.carlosdelachica.timelydigitalclock.model.number.Five;
import com.carlosdelachica.timelydigitalclock.model.number.Four;
import com.carlosdelachica.timelydigitalclock.model.number.Nine;
import com.carlosdelachica.timelydigitalclock.model.number.Null;
import com.carlosdelachica.timelydigitalclock.model.number.One;
import com.carlosdelachica.timelydigitalclock.model.number.Seven;
import com.carlosdelachica.timelydigitalclock.model.number.Six;
import com.carlosdelachica.timelydigitalclock.model.number.Three;
import com.carlosdelachica.timelydigitalclock.model.number.Two;
import com.carlosdelachica.timelydigitalclock.model.number.Zero;

import java.security.InvalidParameterException;

public class NumberUtils {

    public static float[][] getControlPointsFor(int start) {
        switch (start) {
            case (-1):
                return Null.getInstance().getControlPoints();
            case 0:
                return Zero.getInstance().getControlPoints();
            case 1:
                return One.getInstance().getControlPoints();
            case 2:
                return Two.getInstance().getControlPoints();
            case 3:
                return Three.getInstance().getControlPoints();
            case 4:
                return Four.getInstance().getControlPoints();
            case 5:
                return Five.getInstance().getControlPoints();
            case 6:
                return Six.getInstance().getControlPoints();
            case 7:
                return Seven.getInstance().getControlPoints();
            case 8:
                return Eight.getInstance().getControlPoints();
            case 9:
                return Nine.getInstance().getControlPoints();
            default:
                throw new InvalidParameterException("Unsupported number requested");
        }
    }
}
