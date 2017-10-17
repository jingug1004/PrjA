package kr.co.comes.projectA.dao;

import java.util.List;

import kr.co.comes.projectA.dto.AppVO;
import kr.co.comes.projectA.dto.ListCriteria;

public interface AppDAO {
	
	/**
	 * @param vo
	 * @return AppVO ��� �ҷ�����
	 * @throws Exception
	 */
	public List<AppVO> appList(ListCriteria vo) throws Exception;

	/**
	 * @param vo
	 * �� �߰�
	 */
	public void insertApp(AppVO vo);

	/**
	 * @param appid
	 * id���� �������� app ���� 1��
	 * @return
	 */
	public AppVO viewApp(int appid);

	/**
	 * @param appid
	 * �� ����
	 */
	public void deleteApp(int appid);

	/**
	 * @param vo
	 * �� ����
	 */
	public void updateApp(AppVO vo);
	
	/**
	 * @param vo
	 * app ���� summary ī��Ʈ
	 * @return
	 */
	public int countApp(AppVO vo);
	
	/**
	 * @param vo
	 * @return ����¡ ó��
	 * @throws Exception
	 */
	public int listCount(ListCriteria vo) throws Exception;
}
