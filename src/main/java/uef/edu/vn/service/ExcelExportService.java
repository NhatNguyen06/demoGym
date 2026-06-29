package uef.edu.vn.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import uef.edu.vn.model.Member;
import uef.edu.vn.model.Payment;

@Service
public class ExcelExportService {

    private static final DateTimeFormatter DATE_FORMAT
            = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public byte[] exportMembers(List<Member> members) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Members");

            // ===== Header Style =====
            CellStyle headerStyle = workbook.createCellStyle();

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(
                    IndexedColors.DARK_BLUE.getIndex());
            headerStyle.setFillPattern(
                    FillPatternType.SOLID_FOREGROUND);

            // ===== Header =====
            Row header = sheet.createRow(0);

            String[] columns = {
                "ID",
                "Full Name",
                "Email",
                "Phone",
                "Date Of Birth",
                "Gender",
                "Join Date",
                "Status"
            };

            for (int i = 0; i < columns.length; i++) {

                Cell cell = header.createCell(i);

                cell.setCellValue(columns[i]);

                cell.setCellStyle(headerStyle);
            }

            // ===== Data =====
            int rowNum = 1;

            for (Member member : members) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0)
                        .setCellValue(member.getId());

                row.createCell(1)
                        .setCellValue(member.getFullName());

                row.createCell(2)
                        .setCellValue(member.getEmail());

                row.createCell(3)
                        .setCellValue(member.getPhone());

                row.createCell(4)
                        .setCellValue(
                                member.getDob() == null
                                ? ""
                                : member.getDob().format(DATE_FORMAT));

                row.createCell(5)
                        .setCellValue(
                                member.getGender() == null
                                ? ""
                                : member.getGender().name());

                row.createCell(6)
                        .setCellValue(
                                member.getJoinDate() == null
                                ? ""
                                : member.getJoinDate().format(DATE_FORMAT));

                row.createCell(7)
                        .setCellValue(
                                member.getStatus() == null
                                ? ""
                                : member.getStatus().name());
            }

            // ===== Auto Size =====
            for (int i = 0; i < columns.length; i++) {

                sheet.autoSizeColumn(i);

            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            workbook.write(out);

            return out.toByteArray();

        } catch (Exception ex) {

            throw new RuntimeException("Cannot export Excel.", ex);

        }
    }

    public byte[] exportPayments(List<Payment> payments) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Payments");

            //-------------------------------------------------
            // HEADER STYLE (đồng bộ với Members)
            //-------------------------------------------------
            CellStyle headerStyle = workbook.createCellStyle();

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            //-------------------------------------------------
            // HEADER ROW
            //-------------------------------------------------
            Row header = sheet.createRow(0);

            String[] columns = {
                "ID",
                "Amount",
                "Method",
                "Status",
                "Member ID"
            };

            for (int i = 0; i < columns.length; i++) {

                Cell cell = header.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }

            //-------------------------------------------------
            // DATA
            //-------------------------------------------------
            int rowIndex = 1;

            for (Payment payment : payments) {

                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(payment.getId());

                BigDecimal amount = payment.getAmount();
                row.createCell(1).setCellValue(
                        amount == null ? 0 : amount.doubleValue()
                );

                row.createCell(2).setCellValue(
                        payment.getMethod() == null ? "" : payment.getMethod()
                );

                row.createCell(3).setCellValue(
                        payment.getStatus() == null ? "" : payment.getStatus().name()
                );

                row.createCell(4).setCellValue(
                        payment.getMemberId()
                );
            }

            //-------------------------------------------------
            // AUTO SIZE
            //-------------------------------------------------
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            //-------------------------------------------------
            // OUTPUT
            //-------------------------------------------------
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            return out.toByteArray();

        } catch (Exception ex) {
            throw new RuntimeException("Cannot export Excel payments.", ex);
        }
    }
}
