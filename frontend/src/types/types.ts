import axios, { AxiosResponse } from 'axios';

export interface Player {
    id: number;
    firstName: string;
    lastName: string;
    birthDate: any;
    birthCity: string;
    nationality: any;
    team: Team;
    position: any;
}

export interface Team {
    id: number;
    name: string;
    shortName: string;
    coach: any;
    league: League;
}

export interface League {
    id: number,
    name: string
}

export async function getPlayer(id: number): Promise<Player> {
    const url = 'https://localhost:8080/api/player/${id}';

    try {
        const response: AxiosResponse<Player> = await axios.get(url);
        return response.data;
    } catch (error) {
        console.error('Error retrieving player data:', error);
        throw error;
    }
}

export async function createPlayer(player: Player): Promise<Player> {
    const url = 'https://localhost:8080/api/player/create';

    try {
        const response: AxiosResponse<Player> = await axios.post(url, player);
        console.log('Added a new player')
        return response.data;
    } catch (error) {
        console.error('Error retrieving player data:', error);
        throw error;
    }
}

export async function updatePlayer(player: Player): Promise<Player> {
    const url = 'https://localhost:8080/api/player/update';

    try {
        const response: AxiosResponse<Player> = await axios.put(url, player);
        console.log('Updated an existing player')
        return response.data;
    } catch (error) {
        console.error('Error retrieving player data:', error);
        throw error;
    }
}

export async function deletePlayer(id: number): Promise<void> {
    const url = 'https://localhost:8080/api/player/delete/${id}';

    try {
        await axios.delete(url);
        console.log('Player deleted')
    } catch (error) {
        console.error('Error retrieving player data:', error);
        throw error;
    }
}

export async function getAllPlayers(): Promise<Player[]> {
    const url = 'https://localhost:8080/api/player/all';

    try {
        const response: AxiosResponse<Player[]> = await axios.get(url);
        return response.data;
    } catch (error) {
        console.error('Error retrieving player data:', error);
        throw error;
    }
}

export async function getAllTeams(): Promise<Team[]> {
    const url = 'https://localhost:8080/api/team/all';

    try {
        const response: AxiosResponse<Team[]> = await axios.get(url);
        return response.data;
    } catch (error) {
        console.error('Error retrieving player data:', error);
        throw error;
    }
}

export async function updateTeam(team: Team): Promise<Team> {
    const url = 'https://localhost:8080/api/team/update';

    try {
        const response: AxiosResponse<Team> = await axios.put(url, team);
        console.log('Updated an existing team')
        return response.data;
    } catch (error) {
        console.error('Error retrieving player data:', error);
        throw error;
    }
}

export enum PageEnum {
    list,
    add,
    edit,
}