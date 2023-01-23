import React from "react";
import UserCard from "../UserCard";

const UsersContainer = ({ usersList }) => {
  return (
    <div className="userContainer">
      {usersList.length > 0 ? (
        usersList.map((user) => <UserCard user={user} />)
      ) : (
        <p>Users will be displayed here</p>
      )}
    </div>
  );
};

export default UsersContainer;
