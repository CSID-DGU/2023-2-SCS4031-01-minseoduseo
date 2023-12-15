import Papa, { ParseResult } from "papaparse";

type Data = {
  label: string;
  plant_name: string;
  disease: string;
  reason: string;
  solution: string;
  symptoms: string;
  medicine: string;
};

type PlantData = {
  plantName: string;
  image: string;
  plantingSeason: string;
  seedingMethod: string;
  wateringSchedule: string;
  careMethod: string;
  majorDiseases: string;
  harvestingSeason: string;
};

type LabelType<T> = T extends { image: string } ? PlantData : Data;

async function readCSV<T>(csvData: string): Promise<LabelType<T>[]> {
  return new Promise((resolve) => {
    Papa.parse(csvData, {
      delimiter: ",",
      download: true,
      header: true,
      complete: (results: ParseResult<LabelType<T>>) => {
        resolve(results.data || []);
      },
    });
  });
}
export default readCSV;
