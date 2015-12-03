package a8;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PixelInspectorTool implements Tool {

	private PixelInspectorUI ui;
	private ImageEditorModel model;
	private int frameSize = 10;
	
	public PixelInspectorTool(ImageEditorModel model) {
		this.model = model;
		ui = new PixelInspectorUI();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			int x = e.getX();
			int y = e.getY();
			Pixel p = model.getPixel(x, y);
					
			ui.setInfo(x, y, p);
		}
		catch (Exception ex) {
			// Click may have been out of bounds. Do nothing in this case.
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Pixel Inspector";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
		}
		catch (Exception ex) {
			// Click may have been out of bounds. Do nothing in this case.
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		IndirectFrame modelCrop;
		int x = e.getX();
		int y = e.getY();
		int xedge =  model.getCurrent().getWidth();
		int yedge = model.getCurrent().getHeight();

		if ((x + frameSize/2 < model.getCurrent().getWidth() && y + frameSize/2 < model.getCurrent().getHeight()) &&
		   (x - frameSize/2 > 0 && y - frameSize/2 > 0)){
		modelCrop = model.getCurrent().crop(x, y, frameSize, frameSize, frameSize/2);
		ui.changeImagePreview(modelCrop, frameSize);

		}
		
		/*
		else {
			if (x < 0 && y < 0) {
			modelCrop = model.getCurrent().crop(x, y, x, y, x/2);
			}
			if (x < 0 && yedge - y < frameSize/2) {
			modelCrop = model.getCurrent().crop(x, y, x, yedge - y, x/2);
			}
			if (xedge - x < frameSize/2 && y < 0) {
			modelCrop = model.getCurrent().crop(x, y, xedge - x, y, x/2);
			}
			else  {
			modelCrop = model.getCurrent().crop(x, y, xedge - x, yedge - y, );
			}
		}
		*/

	}

}
