package com.scichart.myapplication;

import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.scichart.charting.modifiers.ModifierGroup;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.visuals.annotations.HorizontalAnchorPoint;
import com.scichart.charting.visuals.annotations.TextAnnotation;
import com.scichart.charting.visuals.annotations.VerticalAnchorPoint;
import com.scichart.charting.visuals.axes.IAxis;
import com.scichart.drawing.utility.ColorUtil;
import com.scichart.extensions.builders.SciChartBuilder;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    // Create SciChartSurface using Builders
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SciChartSurface.setRuntimeLicenseKey("FF6zQ0JxELERIxQE4v6Spjpqm2/bui2dBjHjBNELSU0LdRcx" +
                    "0XUNWwr6ZB0fsJNN7EhMGZDd07VVZvwH6OSrR9R6mVxuZxYhdRdwl76USE1Tz2PWo1SWY3gxV3tg/t+" +
                    "p5RFsZL0jIMl3HR8EzSKtWQWPPyU7eQaNyRsCHCDfWvfAjx4kNLNW15o8BkBWmEv1G4cZarcIoCgLdzQv" +
                    "1CzdfY9UbF3d25Udg2ojjiAeMEgjpAvFlrEF/ECw3QG5Ygr4ZiWmqp75jsAWZ0cY/2Hw9FOOA+JNCuEo8G7" +
                    "+UXTrmcgbS+N37HTCCg0W4WAW1YdFxLSkHJeBqMxdz27DxA7kOd1ycExpp6QH8hs+Am3CDGZ9UNCeKk3/7p" +
                    "zq5kKLQo2ZsBGQiLnPLlFzkHL620Y0ezFDmdh2EuUpRl0wjHZRSebkhD//xe1iKEqX8ECM6DN4VgVe2MHEkl" +
                    "yCQLGCnunfSlpef2ucNk9VYwDovap9l541/qhaEqdOZ2idcaaihHl8fQMGL85FtQa/i/YnAF2r");
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
                .withAxisTitle("Merry Christmas!")
                .withVisibleRange(-5, 15)
                .build();

        // Create a numeric Y axis
        final IAxis yAxis = sciChartBuilder.newNumericAxis()
                .withAxisTitle("2022").withVisibleRange(0, 80).build();

        // Create a TextAnnotation and specify the inscription and position for it
        TextAnnotation textAnnotation = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(55.0)
                .withText("^")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(40, ColorUtil.Green)
                .build();

        TextAnnotation textAnnotation2 = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(50.0)
                .withText("^ ^")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(40, ColorUtil.Green)
                .build();

        TextAnnotation textAnnotation3 = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(45.0)
                .withText("^ ^ ^")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(40, ColorUtil.Green)
                .build();

        TextAnnotation textAnnotation4 = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(40.0)
                .withText("^ ^ ^ ^")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(40, ColorUtil.Green)
                .build();

        TextAnnotation textAnnotation5 = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(35.0)
                .withText("^ ^ ^ ^ ^")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(40, ColorUtil.Green)
                .build();

        TextAnnotation textAnnotation6 = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(30.0)
                .withText("^ ^ ^ ^ ^ ^")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(40, ColorUtil.Green)
                .build();

        TextAnnotation textAnnotation7 = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(20.0)
                .withText("HO HO HO!")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(36, ColorUtil.Red)
                .build();

        TextAnnotation textAnnotation8 = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(60.0)
                .withText("o")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(26, ColorUtil.White)
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
        Collections.addAll(surface.getAnnotations(),
                textAnnotation, textAnnotation2,
                textAnnotation3, textAnnotation4,
                textAnnotation5, textAnnotation6,
                textAnnotation7, textAnnotation8);

        // Add the interactions to the ChartModifiers collection of the surface
        Collections.addAll(surface.getChartModifiers(), chartModifiers);
    }
}