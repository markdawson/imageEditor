package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PixelInspectorUI extends JPanel implements ChangeListener {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JButton copy_button;
	private Pixel p;
	private FrameView image_preview;
	private JPanel label_panel;
	private JPanel inspector_panel;

	private boolean firstPixelInspectedYet = false;

	public PixelInspectorUI() {
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");
		copy_button = new JButton("Copy Pixel to Paint Brush");

		label_panel = new JPanel();

		label_panel.setLayout(new GridLayout(4,1));
		label_panel.add(x_label);
		label_panel.add(y_label);
		label_panel.add(pixel_info);
		label_panel.add(copy_button);

		copy_button.addChangeListener(this);

		inspector_panel = new JPanel();
		inspector_panel.add(label_panel, BorderLayout.CENTER);

		image_preview = new FrameView(new ColorFrame(50,50));
		inspector_panel.add(image_preview, BorderLayout.EAST);

		add(inspector_panel);

	}

	public void setInfo(int x, int y, Pixel p) {
		firstPixelInspectedYet = true;
		this.p = p;
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));		
	}


	@Override
	public void stateChanged(ChangeEvent arg0) {
		if (firstPixelInspectedYet) {
			PaintBrushToolUI.setBrushColor(p);
		}
	}

	public void changeImagePreview(IndirectFrame modelCrop, int frameSize) {

		Frame preview_frame = image_preview.getFrame();
		preview_frame.suspendNotifications();
		for (int i=0; i<50; i=i+5) {
			for (int j=0; j<50; j=j+5) {

				Pixel p = modelCrop.getPixel(i/5, j/5);

				for(int q = 0; q<5; q++) {
					for (int r = 0; r<5; r++){
						if (i+r > 50 || j+q > 50) {
							preview_frame.setPixel(i+r, j+q, p);
						}
						preview_frame.setPixel(i+r, j+q, p);
						
					}
				}
			}
		}

		preview_frame.resumeNotifications();
	}
}



