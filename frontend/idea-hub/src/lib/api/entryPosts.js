import qs from "qs";
import client from "./client";

export const entryWritePost = ({
  title,
  body,
  taggedContest,
  taggedContestID,
  videoURL,
  team,
  status,
  github,
}) =>
  client.post("/api/entrys", {
    title,
    body,
    taggedContest,
    taggedContestID,
    videoURL,
    team,
    status,
    github,
  });

export const readEntry = (id) => client.get(`/api/entrys/${id}`);

export const listEntrys = ({ page, taggedContestID }) => {
  const queryString = qs.stringify({
    page,
    taggedContestID,
  });
  return client.get(`/api/entrys?${queryString}`, {
    params: {
      taggedContestID: queryString.taggedContestID,
    },
  });
};

export const updateEntry = ({
  id,
  title,
  body,
  taggedContest,
  taggedContestID,
  videoURL,
  team,
  status,
  stars,
  github,
}) =>
  client.patch(`/api/entrys/${id}`, {
    title,
    body,
    taggedContest,
    taggedContestID,
    videoURL,
    team,
    status,
    stars,
    github,
  });

export const removeEntry = (id) => client.delete(`/api/entrys/${id}`);

//star 버튼 클릭 시 현재 작품의 star에 1을 더하고, star를 누른 유저 목록에 현재 로그인한 유저 등록.
export const giveStar = ({ entry, user }) => {
  //로그인 한 상태일 때
  if (user) {
    const { _id, stars, star_edUser } = entry;
    //star누른 유저 목록에 현재 로그인한 유저가 없을 경우에만.
    const isInList = star_edUser.find((item) => item === user._id);
    if (!isInList) {
      return client.patch(`/api/entrys/${_id}/star`, {
        stars: stars + 1,
        star_edUser: [...star_edUser, user._id],
      });
    }
  }
};

//star를 취소
export const unStar = ({ entry, user }) => {
  //로그인 한 상태일 때
  if (user) {
    const { _id, stars, star_edUser } = entry;
    //star누른 유저 목록에 현재 로그인한 유저가 없을 경우에만.
    const indexOfItem = star_edUser.findIndex((item) => item === user._id);
    //유저 목록에 현재 유저가 존재 할 때, unStar 작업 진행.
    if (indexOfItem > -1) {
      let star_edUserCopy = JSON.parse(JSON.stringify(star_edUser));
      star_edUserCopy.splice(indexOfItem, 1);

      return client.patch(`/api/entrys/${_id}/star`, {
        stars: stars - 1,
        star_edUser: star_edUserCopy,
      });
    }
  }
};

//수상 정보를 업데이트(디폴트: '-')
export const updatePrize = ({ entry, priority }) => {
  const { _id } = entry;
  return client.patch(`/api/entrys/${_id}`, {
    prizedPlace: priority,
  });
};
