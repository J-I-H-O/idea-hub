import React, { useEffect } from "react";
import EntryWriteActionButtons from "../../components/entryWrite/EntryWriteActionButton";
import { useSelector, useDispatch } from "react-redux";
import { withRouter } from "react-router-dom";
import { entryWritePost, updateEntry } from "../../modules/entryWrite";

const EntryWriteActionButtonsContainer = ({ history }) => {
  const dispatch = useDispatch();
  const {
    title,
    body,
    taggedContest,
    taggedContestID,
    videoURL,
    team,
    status,
    github,
    entry,
    entryError,
    originalEntryId,
  } = useSelector(({ entryWrite }) => ({
    title: entryWrite.title,
    body: entryWrite.body,
    taggedContest: entryWrite.taggedContest,
    taggedContestID: entryWrite.taggedContestID,
    videoURL: entryWrite.videoURL,
    team: entryWrite.team,
    status: entryWrite.status,
    github: entryWrite.github,
    entry: entryWrite.entry,
    entryError: entryWrite.entryError,
    originalEntryId: entryWrite.originalEntryId,
  }));

  const onPublish = () => {
    if (originalEntryId) {
      dispatch(
        updateEntry({
          title,
          body,
          taggedContest,
          taggedContestID,
          videoURL,
          team,
          status,
          github,
          id: originalEntryId,
        })
      );
      return;
    }
    dispatch(
      entryWritePost({
        title,
        body,
        taggedContest,
        taggedContestID,
        videoURL,
        team,
        status,
        github,
      })
    );
  };

  const onCancel = () => {
    history.goBack();
  };

  useEffect(() => {
    if (entry) {
      const { _id, user } = entry;
      history.push(`/entry/${_id}`);
    }
    if (entryError) {
      console.log(entryError);
    }
  }, [history, entry, entryError]);
  return (
    <EntryWriteActionButtons
      onPublish={onPublish}
      onCancel={onCancel}
      isEdit={!!originalEntryId}
    />
  );
};

export default withRouter(EntryWriteActionButtonsContainer);
