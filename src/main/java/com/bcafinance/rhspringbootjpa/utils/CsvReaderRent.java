package com.bcafinance.rhspringbootjpa.utils;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 06/12/2022
@Last Modified 06/12/2022 10:46
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.models.Airport;
import com.bcafinance.rhspringbootjpa.models.Rent;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderRent {

    public static boolean isCsv(MultipartFile multipartFile)
    {
        if(!ConstantMessage.CONTENT_TYPE_CSV.equals(multipartFile.getContentType()))
        {
            return false;
        }
        return true;
    }

    public static List<Rent> csvToRentData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Rent> lsRent = new ArrayList<Rent>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                Rent rent = new Rent();
                rent.setCarModel(record.get("CarModel"));
                rent.setCarUser(record.get("CarUser"));
                rent.setCarNumber(Integer.valueOf(record.get("CarNumber")));
                rent.setCarYear(Integer.valueOf(record.get("CarYear")));
                rent.setCost(Double.valueOf(record.get("Cost")));
                rent.setPenalty(Double.valueOf(record.get("Penalty")));
                rent.setLoanDate(LocalDate.parse(record.get("LoanDate")));
                rent.setReturnDate(LocalDate.parse(record.get("ReturnDate")));
                lsRent.add(rent);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return lsRent;
        }
    }

}
