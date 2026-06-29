package uef.edu.vn.service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;

import java.io.ByteArrayOutputStream;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import org.springframework.stereotype.Service;

import uef.edu.vn.model.Member;
import uef.edu.vn.model.Payment;

@Service
public class PdfExportService {

    public byte[] exportMembers(List<Member> members) {

        try {

            ByteArrayOutputStream output
                    = new ByteArrayOutputStream();

            Document document
                    = new Document();

            PdfWriter.getInstance(document, output);

            document.open();

            //-------------------------------------------------
            // TITLE
            //-------------------------------------------------
            Font titleFont
                    = FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            18,
                            Color.BLUE);

            Paragraph title
                    = new Paragraph(
                            "GYM MEMBER REPORT",
                            titleFont);

            title.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(title);

            document.add(new Paragraph(" "));

            //-------------------------------------------------
            // Export Time
            //-------------------------------------------------
            Font normalFont
                    = FontFactory.getFont(
                            FontFactory.HELVETICA,
                            11);

            document.add(
                    new Paragraph(
                            "Export Time : "
                            + LocalDateTime.now().format(
                                    DateTimeFormatter.ofPattern(
                                            "dd/MM/yyyy HH:mm")),
                            normalFont));

            document.add(new Paragraph(" "));

            //-------------------------------------------------
            // TABLE
            //-------------------------------------------------
            PdfPTable table
                    = new PdfPTable(8);

            table.setWidthPercentage(100);

            table.setWidths(new float[]{
                1.2f,
                3.5f,
                4f,
                3f,
                2.5f,
                2f,
                2.5f,
                2.5f
            });

            //-------------------------------------------------
            // HEADER
            //-------------------------------------------------
            addHeader(table, "ID");
            addHeader(table, "Full Name");
            addHeader(table, "Email");
            addHeader(table, "Phone");
            addHeader(table, "DOB");
            addHeader(table, "Gender");
            addHeader(table, "Join Date");
            addHeader(table, "Status");

            //-------------------------------------------------
            // DATA
            //-------------------------------------------------
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Member member : members) {

                table.addCell(String.valueOf(member.getId()));

                table.addCell(member.getFullName());

                table.addCell(member.getEmail());

                table.addCell(member.getPhone());

                table.addCell(
                        member.getDob() == null
                        ? ""
                        : member.getDob().format(formatter));

                table.addCell(
                        member.getGender() == null
                        ? ""
                        : member.getGender().name());

                table.addCell(
                        member.getJoinDate() == null
                        ? ""
                        : member.getJoinDate().format(formatter));

                table.addCell(
                        member.getStatus() == null
                        ? ""
                        : member.getStatus().name());

            }

            document.add(table);

            document.close();

            return output.toByteArray();

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Cannot export PDF.",
                    ex);

        }

    }

    //---------------------------------------------------------
    // Header Cell
    //---------------------------------------------------------
    private void addHeader(
            PdfPTable table,
            String title) {

        Font font
                = FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD);

        font.setColor(Color.WHITE);

        PdfPCell cell
                = new PdfPCell(
                        new Phrase(title, font));

        cell.setBackgroundColor(
                new Color(30, 144, 255));

        cell.setHorizontalAlignment(
                PdfPCell.ALIGN_CENTER);

        cell.setPadding(6);

        table.addCell(cell);

    }

    public byte[] exportPayments(List<Payment> payments) {

        try {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();

            //-------------------------------------------------
            // TITLE (giống Member)
            //-------------------------------------------------
            Font titleFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    18,
                    Color.BLUE
            );

            Paragraph title = new Paragraph("PAYMENT REPORT", titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(title);
            document.add(new Paragraph(" "));

            //-------------------------------------------------
            // EXPORT TIME (giống Member)
            //-------------------------------------------------
            Font normalFont = FontFactory.getFont(
                    FontFactory.HELVETICA,
                    11
            );

            document.add(new Paragraph(
                    "Export Time : "
                    + LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                    ),
                    normalFont
            ));

            document.add(new Paragraph(" "));

            //-------------------------------------------------
            // TABLE
            //-------------------------------------------------
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            table.setWidths(new float[]{
                2f, 3f, 3f, 2.5f, 2.5f
            });

            //-------------------------------------------------
            // HEADER (đồng bộ style với Member)
            //-------------------------------------------------
            addHeader(table, "ID");
            addHeader(table, "Amount");
            addHeader(table, "Method");
            addHeader(table, "Status");
            addHeader(table, "Member ID");

            //-------------------------------------------------
            // DATA
            //-------------------------------------------------
            for (Payment payment : payments) {

                table.addCell(String.valueOf(payment.getId()));

                table.addCell(
                        payment.getAmount() == null
                        ? "0"
                        : payment.getAmount().toPlainString()
                );

                table.addCell(
                        payment.getMethod() == null
                        ? ""
                        : payment.getMethod()
                );

                table.addCell(
                        payment.getStatus() == null
                        ? ""
                        : payment.getStatus().name()
                );
                table.addCell(String.valueOf(payment.getMemberId()));
            }

            document.add(table);
            document.close();

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Cannot export payment PDF", e);
        }
    }

}
