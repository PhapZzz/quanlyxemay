package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class RoundButton extends JButton {
    private int radius;
    private Color borderColor = Color.BLACK; // Màu viền mặc định
    private int borderWidth = 0; // Độ dày viền mặc định

    public RoundButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setFocusPainted(false);
        setOpaque(false);
    }

    // Thiết lập kích thước nút
    public void setButtonSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setSize(width, height);
        revalidate(); // Cập nhật bố cục
        repaint(); // Vẽ lại button
    }

    // Thiết lập màu viền
    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    // Thiết lập độ dày viền
    public void setBorderWidth(int width) {
        this.borderWidth = width;
        repaint();
    }

    // Phương thức vẽ viền
    private void drawBorder(Graphics2D g2) {
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderWidth));
        g2.drawRoundRect(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth, radius, radius);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền button
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Vẽ viền button
        drawBorder(g2);

        setBorder(new EmptyBorder(0, 5, 0, 5));
        g2.dispose();
        super.paintComponent(g);
    }
    public void setFont(int size,Color color)
    {
        setFont(new Font("Arial",Font.BOLD,size));
        setForeground(color);
    }
}
