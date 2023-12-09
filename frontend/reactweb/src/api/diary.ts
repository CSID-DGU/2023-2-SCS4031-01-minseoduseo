import { axiosInterface, currentTime } from "api";
interface postDiaryProps {
  title: string;
  plantName: string;
  contents: string;
  date?: string;
}
interface getDiaryListProps {
  year: number;
  month: number;
}
interface putDiaryProps {
  title: string;
  plantName: string;
  contents: string;
  diaryId: number;
}
const DIARY_URL = "/api/member/diary";

const getDiaryList = async ({ year, month }: getDiaryListProps) => {
  try {
    const { data } = await axiosInterface.get(`${DIARY_URL}/monthly`, {
      params: {
        year,
        month,
      },
    });
    return data;
  } catch (e: any) {
    return [];
  }
};

const postDiary = async ({
  title,
  plantName,
  contents,
  date,
}: postDiaryProps) => {
  console.log(date);
  const { data } = await axiosInterface.post(`${DIARY_URL}`, {
    createDate: date,
    title,
    plantName,
    contents,
  });
  return data;
};

const getDiary = async (diaryId: number) => {
  const { data } = await axiosInterface.get(`${DIARY_URL}/detail`, {
    params: {
      diaryId,
    },
  });
  return data;
};

const putDiary = async ({
  title,
  plantName,
  contents,
  diaryId,
}: putDiaryProps) => {
  const { data } = await axiosInterface.put(
    `${DIARY_URL}`,
    {
      createDate: currentTime,
      title,
      plantName,
      contents,
    },
    {
      params: {
        diaryId,
      },
    }
  );
  return data;
};

const delDiary = async (diaryId: number) => {
  const { data } = await axiosInterface.delete(`${DIARY_URL}`, {
    params: {
      diaryId,
    },
  });
  return data;
};

export { postDiary, getDiary, getDiaryList, putDiary, delDiary };
