import java.awt.*;
import javax.swing.border.Border;

public class test1 implements Border {
    private final int thickness;
    private final Color shadowColor;

    public test1(int thickness, Color shadowColor) {
        this.thickness = thickness;
        this.shadowColor = shadowColor;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness, thickness, thickness, thickness);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int shadowSize = thickness;
        int w = width;
        int h = height;

        // Paint radial shadow
        for (int i = 0; i < shadowSize; i++) {
            float opacity = 1f - (float) i / shadowSize;
            g2.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), (int)(opacity * 255)));
            g2.drawRoundRect(
                x + i,
                y + i,
                w - i * 2 - 1,
                h - i * 2 - 1,
                20, 20
            );
        }

        g2.dispose();
    }
}
