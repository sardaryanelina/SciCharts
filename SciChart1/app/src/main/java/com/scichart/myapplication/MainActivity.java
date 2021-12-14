package com.scichart.myapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Surface;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.scichart.charting.ClipMode;
import com.scichart.charting.model.dataSeries.XyDataSeries;
import com.scichart.charting.modifiers.AxisDragModifierBase;
import com.scichart.charting.modifiers.ModifierGroup;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.visuals.annotations.HorizontalAnchorPoint;
import com.scichart.charting.visuals.annotations.TextAnnotation;
import com.scichart.charting.visuals.annotations.VerticalAnchorPoint;
import com.scichart.charting.visuals.axes.AxisAlignment;
import com.scichart.charting.visuals.axes.IAxis;
import com.scichart.charting.visuals.pointmarkers.EllipsePointMarker;
import com.scichart.charting.visuals.renderableSeries.IRenderableSeries;
import com.scichart.core.annotations.Orientation;
import com.scichart.core.framework.UpdateSuspender;
import com.scichart.core.model.DoubleValues;
import com.scichart.drawing.utility.ColorUtil;
import com.scichart.extensions.builders.SciChartBuilder;

import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    // Create SciChartSurface using Builders
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Xml License Key for use in Mobile SDK v2 or earlier
//        try{
//            SciChartSurface.setRuntimeLicenseKeyFromResource(this, "app\\src\\main\\res\\raw\\license.xml");
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        // License Key for use in SciChart Mobile SDK v3 or later
        // The call to setRuntimeLicenseKeyFromResource(…) should be enclosed in a try/catch block,
        // because an exception may be thrown if the license file isn’t found.
        try {
            SciChartSurface.setRuntimeLicenseKey("FF6zQ0JxELERIxQE4v6Spjpqm2/bui2dBjHjBNELSU0LdRcx0XUNWwr6ZB0fsJNN7EhMGZDd07VVZvwH6OSrR9R6mVxuZxYhdRdwl76USE1Tz2PWo1SWY3gxV3tg/t+p5RFsZL0jIMl3HR8EzSKtWQWPPyU7eQaNyRsCHCDfWvfAjx4kNLNW15o8BkBWmEv1G4cZarcIoCgLdzQv1CzdfY9UbF3d25Udg2ojjiAeMEgjpAvFlrEF/ECw3QG5Ygr4ZiWmqp75jsAWZ0cY/2Hw9FOOA+JNCuEo8G7+UXTrmcgbS+N37HTCCg0W4WAW1YdFxLSkHJeBqMxdz27DxA7kOd1ycExpp6QH8hs+Am3CDGZ9UNCeKk3/7pzq5kKLQo2ZsBGQiLnPLlFzkHL620Y0ezFDmdh2EuUpRl0wjHZRSebkhD//xe1iKEqX8ECM6DN4VgVe2MHEklyCQLGCnunfSlpef2ucNk9VYwDovap9l541/qhaEqdOZ2idcaaihHl8fQMGL85FtQa/i/YnAF2r");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a SciChartSurface
        SciChartSurface surface = new SciChartSurface(this);

        // Get a layout declared in "activity_main.xml" by id
        LinearLayout chartLayout = (LinearLayout) findViewById(R.id.chart_layout);
        // Add the SciChartSurface to the layout
        chartLayout.addView(surface);
        // Initialize the SciChartBuilder
        SciChartBuilder.init(this);
        // Obtain the SciChartBuilder instance
        final SciChartBuilder sciChartBuilder = SciChartBuilder.instance();
        // Create a numeric X axis
        final IAxis xAxis = sciChartBuilder.newNumericAxis()
                .withAxisTitle("X Axis Title")
                .withVisibleRange(-5, 15)
                .build();
        // Create a numeric Y axis
        final IAxis yAxis = sciChartBuilder.newNumericAxis()
                .withAxisTitle("Y Axis Title").withVisibleRange(0, 100).build();
        // Create a TextAnnotation and specify the inscription and position for it
        TextAnnotation textAnnotation = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(55.0)
                .withText("Hello World!")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(20, ColorUtil.White)
                .build();
        // Create interactivity modifiers
        ModifierGroup chartModifiers = sciChartBuilder.newModifierGroup()
                .withPinchZoomModifier().withReceiveHandledEvents(true).build()
                .withZoomPanModifier().withReceiveHandledEvents(true).build()
                .build();
        // Add the Y axis to the YAxes collection of the surface
        Collections.addAll(surface.getYAxes(), yAxis);
        // Add the X axis to the XAxes collection of the surface
        Collections.addAll(surface.getXAxes(), xAxis);
        // Add the annotation to the Annotations collection of the surface
        Collections.addAll(surface.getAnnotations(), textAnnotation);
        // Add the interactions to the ChartModifiers collection of the surface
        Collections.addAll(surface.getChartModifiers(), chartModifiers);
    }
}