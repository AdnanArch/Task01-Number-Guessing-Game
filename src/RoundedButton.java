import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class RoundedButton extends JButton {
    private final Color backgroundColor;
    private final Color hoverColor;
    private final Color pressedColor;

    public RoundedButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setBackground(new Color(71, 135, 229));
        setFocusPainted(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(150, 40));

        // Set the colors for different button states
        backgroundColor = new Color(71, 135, 229);
        hoverColor = new Color(71, 135, 229).brighter();
        pressedColor = new Color(71, 135, 229).darker();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(pressedColor);
        } else if (getModel().isRollover()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(backgroundColor);
        }

        int cornerRadius = 20;
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

        super.paintComponent(g2);
        g2.dispose();
    }
}

