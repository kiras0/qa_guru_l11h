import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DisplayName("Testing Files in ZIP archive")
public class ZipTest {
    private final ClassLoader cl = ZipTest.class.getClassLoader();

    @Test
    @DisplayName("Checking PDF file in ZIP")
    void PdfInZipFileTest() throws Exception{
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("sampleZip.zip")))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")){
                    PDF pdf = new PDF(zis);
                    assertThat(pdf.text).contains("This is an example of a user fillable PDF form");
                    break;
                }
            }
        }
    }
    @Test
    @DisplayName("Checking CSV file in ZIP")
    void CvsInZipFileTest() throws Exception{
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("sampleZip.zip")))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".cvs")){
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> csvContent = csvReader.readAll();
                    Assertions.assertEquals(new String[]{"Game Number"}, csvContent.get(1));
                    break;

                }
            }
        }
    }
    @Test
    @DisplayName("Checking Excel file in ZIP")
    void XlsInZipFileTest() throws Exception{
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("sampleZip.zip")))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".xls")){
                    XLS xls = new XLS(zis);
                    Assertions.assertEquals(xls.excel.getSheetAt(0).getRow(1).getCell(4)
                            .getStringCellValue(), "This is a sample description");
                    break;
                }
            }
        }
    }
}