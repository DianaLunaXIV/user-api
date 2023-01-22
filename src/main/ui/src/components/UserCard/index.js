import React from "react";

const UserCard = ({ user }) => {
  return (
    <div className="userCard">
      <h1>
        {user.firstName} {user.lastName}
      </h1>
      <div className="userInfo">
        <p>User ID: {user.id}</p>
        <p>Profession: {user.profession}</p>
        <p>Email: {user.email}</p>
        <p>City: {user.city}</p>
        <p>Country: {user.country}</p>
        <p>Date created: {user.dateCreated}</p>
      </div>
    </div>
  );
};

export default UserCard;
