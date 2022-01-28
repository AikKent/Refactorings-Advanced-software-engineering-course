/**
 * 
 */
package carcassonne.view.menubar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import carcassonne.view.main.MainView;
import carcassonne.view.main.ZoomMode;

/**
 * Smoothing event and mouse listener for the zoom slider, which smoothes the dragging by limiting the updates to
 * certain steps.
 * @author Timur Saglam
 */
public class ZoomSliderListener extends MouseAdapter implements ChangeListener {

    private static final double SMOOTHING_FACTOR = 5.0; // only update zoom with this step size.
    private final MainView mainView;
    private final JSlider slider;
    private int previousValue;
    private boolean blockingEvents;

    /**
     * Creates the listener.
     * @param mainView is the main user interface, needed for zooming.
     * @param slider is the slider, needed for the values.
     */
    public ZoomSliderListener(MainView mainView, JSlider slider) {
        this.mainView = mainView;
        this.slider = slider;
        previousValue = -1;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        mainView.setZoom(slider.getValue(), ZoomMode.SMOOTH);
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        int smoothedValue = (int) (Math.round(slider.getValue() / SMOOTHING_FACTOR) * SMOOTHING_FACTOR);
        if (previousValue != smoothedValue && !blockingEvents) { // limit zoom updated when dragging.
            previousValue = smoothedValue;
            mainView.setZoom(smoothedValue, ZoomMode.FAST);
        }
    }

    /**
     * Checks if events are blocked.
     * @return the blockingEvents
     */
    public boolean isBlockingEvents() {
        return blockingEvents;
    }

    /**
     * Blocks or unblocks all events.
     * @param blockingEvents specifies if events are blocked or not.
     */
    public void setBlockingEvents(boolean blockingEvents) {
        this.blockingEvents = blockingEvents;
    }
}
