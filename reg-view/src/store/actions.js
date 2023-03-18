//调用mutations
export const setUser = ({commit},user) => {
    commit("userStatus",user);
}
export const setToken = ({commit},token) =>{
    commit("setToken",token);
}



export const setAdmin = ({commit},admin) => {
    commit("AdminStatus",admin);
}
export const setAdminToken = ({commit},token) =>{
    commit("setAdminToken",token);
}