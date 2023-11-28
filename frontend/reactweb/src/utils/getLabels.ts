import Papa, { ParseResult } from "papaparse";
import { useEffect, useState } from "react";
import labels from "../assets/files/label_data.csv";
type Data = {
  label: string;
  plant_name: string;
  disease: string;
  reason: string;
  solution: string;
  symptoms: string;
  medicine: string;
};

const useReadCSV = () => {
  const [data, setData] = useState<Data[] | undefined>();
  const getCSV = () =>
    Papa.parse(labels, {
      delimiter: ",",
      download: true,
      header: true,
      complete: (results: ParseResult<Data>) => {
        setData(results.data); // Update this line
      },
    });

  useEffect(() => {
    getCSV();
  }, []);
  return data;
};

export default useReadCSV;
