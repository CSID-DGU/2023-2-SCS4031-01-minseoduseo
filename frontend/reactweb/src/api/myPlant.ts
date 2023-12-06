import { axiosInterface } from "./index";

interface plantInfo {
  color: string;
  name: string;
  nickname: string;
  createDate: string;
}

const putPlant = async (
  plantId: number,
  { color, name, nickname, createDate }: plantInfo
) => {
  try {
    const res = await axiosInterface.put(
      "/api/member/plant",
      {
        color,
        name,
        nickname,
        createDate,
      },
      {
        params: {
          plantId,
        },
      }
    );
    return res.status;
  } catch (err: Error | any) {
    return err.request.status;
  }
};

const deletePlant = async (plantId: number) => {
  try {
    const res = await axiosInterface.delete("/api/member/plant", {
      params: {
        plantId,
      },
    });
    return res.status;
  } catch (err: Error | any) {
    return err.request.status;
  }
};

const getPlantDetail = async (plantId: number) => {
  try {
    const { data } = await axiosInterface.get(`/api/member/plant`, {
      params: {
        plantId,
      },
    });
    return data;
  } catch {
    return {};
  }
};

const getPlants = async () => {
  try {
    const { data } = await axiosInterface.get("/api/member/plants");
    return data;
  } catch {
    return {};
  }
};

const postPlant = async ({ color, name, nickname, createDate }: plantInfo) => {
  try {
    const res = await axiosInterface.post("/api/member/plant", {
      color,
      name,
      nickname,
      createDate,
    });
    return res.status;
  } catch (err: Error | any) {
    return err.request.status;
  }
};

export { getPlants, postPlant, getPlantDetail, putPlant, deletePlant };
